from flask import Flask, request, Response, jsonify
import os
import uuid
from flask_cors import CORS

app = Flask(__name__)
CORS(app)  # Enable CORS for all routes

UPLOAD_FOLDER = 'uploads'
ALLOWED_EXTENSIONS_VIDEO = {'mp4'}
ALLOWED_EXTENSIONS_IMAGE = {'png', 'jpg', 'jpeg','jfif', 'gif'}

app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

def allowed_file(filename, allowed_extensions):
    return '.' in filename and \
        filename.rsplit('.', 1)[1].lower() in allowed_extensions

@app.route('/upload-video', methods=['POST'])
def upload_video():
    if 'file' not in request.files:
        return jsonify({'error': 'No file part'})
    file = request.files['file']
    if file.filename == '':
        return jsonify({'error': 'No selected file'})
    if file and allowed_file(file.filename, ALLOWED_EXTENSIONS_VIDEO):
        filename = str(uuid.uuid4()) + os.path.splitext(file.filename)[1]  # UUID with original extension
        file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
        return jsonify({'url': f"{request.url_root}{UPLOAD_FOLDER}/{filename}"})
    return jsonify({'error': 'File type not allowed for video'})

@app.route('/upload-image', methods=['POST'])
def upload_image():
    if 'file' not in request.files:
        return jsonify({'error': 'No file part'})
    file = request.files['file']
    if file.filename == '':
        return jsonify({'error': 'No selected file'})
    if file and allowed_file(file.filename, ALLOWED_EXTENSIONS_IMAGE):
        filename = str(uuid.uuid4()) + os.path.splitext(file.filename)[1]  # UUID with original extension
        file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
        return jsonify({'url': f"{request.url_root}{UPLOAD_FOLDER}/{filename}"})
    return jsonify({'error': 'File type not allowed for image'})

def generate_video(filepath):
    CHUNK = 1024
    with open(filepath, 'rb') as f:
        while True:
            data = f.read(CHUNK)
            if not data:
                break
            yield data

@app.route('/uploads/<filename>')
def video_stream(filename):
    filepath = os.path.join(app.config['UPLOAD_FOLDER'], filename)
    return Response(generate_video(filepath), mimetype="video/*")

if __name__ == '__main__':
    app.run(debug=True)
