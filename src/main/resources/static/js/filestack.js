"use strict";


console.log("hello from main.js!")

const client = filestack.init(filestackToken);
const options = {
    onUploadDone: updateForm,
    maxSize: 10 * 1024 * 1024,
    accept: 'image/*',
    uploadInBackground: false
};

const picker = client.picker(options);

// get DOM elements

const form = document.getElementById('form-picker');
const fileInput = document.getElementById('fileupload');
const pickerBtn = document.getElementById('picker');
const nameBox = document.getElementById('nameBox');
const urlBox = document.getElementById('urlBox');

// add event listeners

pickerBtn.addEventListener('click', function (e) {
    e.preventDefault();
    picker.open();
});

form.addEventListener('submit', function (e) {
    e.preventDefault();
    alert('Submitting: ' + fileInput.value)
});

// Helper to overwrite input value

function updateForm (result) {
    const fileData = result.filesUploaded[0];
    fileInput.value = fileData.url;

    const name = document.createTextNode('Selected: ' + fileData.filename);
    const url = document.createElement('a');
    url.href = fileDate.url;
    url.appendChild(document.createTextNode(fileData.url));
    nameBox.appendChild(name);
    urlBox.appendChild(document.createTextNode('Upload to: '));
    urlBox.appendChild(url);
}

