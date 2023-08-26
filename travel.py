from flask import Flask, request, jsonify
import requests
import gevent
from gevent import monkey

app = Flask(_name_)

# Monkey-patch to allow asynchronous requests
monkey.patch_all()

def fetch_numbers(url):
    try:
        response = requests.get(url, timeout=5)
        if response.status_code == 200:
            return response.json().get("numbers", [])
    except requests.Timeout:
        pass
    return []

@app.route('/numbers')
def get_numbers():
    urls = request.args.getlist('url')
    
    def fetch_and_merge_numbers(url):
        return fetch_numbers(url)
    
    threads = [gevent.spawn(fetch_and_merge_numbers, url) for url in urls]
    gevent.joinall(threads, timeout=5)
    
    merged_numbers = list(set().union(*[thread.value for thread in threads]))
    merged_numbers.sort()
    
    return jsonify({"numbers": merged_numbers})

if _name_ == '_main_':
    app.run(debug=True)