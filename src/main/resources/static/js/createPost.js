"use strict";

(function(){

    let validPostTitle = false;
    let validPostBody = false;
    let validCategory = false;

    let createPostTitle = document.querySelector('#createPostTitle');
    let createPostTitleWarning = document.querySelector('#createPostTitleWarning');
    let createPostBody = document.querySelector('#createPostBody');
    let createPostBodyWarning = document.querySelector('#createPostBodyWarning');
    // let dropdownMenuButton = document.querySelector('#dropdownMenuButton');
    // let checkbox = document.querySelector('#checkbox');
    let checkboxWarning = document.querySelector('#checkboxWarning');
    let category = document.querySelector('input[name=checked]');
    let categories = document.querySelector('#categories');

    createPostTitle.addEventListener('keyup', declareValidPostTitle);
    createPostTitle.addEventListener('keyup', allowSubmission);
    createPostBody.addEventListener('keyup', declareValidPostBody);
    createPostBody.addEventListener('keyup', allowSubmission);
    category.addEventListener('click', validateDropdown);
    category.addEventListener('click', allowSubmission);


    function declareValidPostTitle() {
        if (createPostTitle.value.length < 1 || createPostTitle.value.length > 20) {
            createPostTitleWarning.innerHTML = "<p class='text-danger'>oops! Post title must be no more than 20 characters</p>"
            validPostTitle = false;
        } else {
            createPostTitleWarning.innerHTML = "<p class='text-success'>Success!</p>"
            validPostTitle = true;
            allowSubmission();
        }
    }

    function declareValidPostBody() {
        if (createPostBody.value.length < 1) {
            createPostBodyWarning.innerHTML = "<p class='text-danger'>oh no! Post body cannot be empty.</p>"
            validPostBody = false;
        } else {
            createPostBodyWarning.innerHTML = "<p class='text-success'>Success!</p>"
            validPostBody = true;
            allowSubmission();
        }
    }

    function validateDropdown() {

        if (category.checked === false) {
            checkboxWarning.innerHTML = "<p class='text-danger'>oh? Please choose at least 1 category.</p>"
            validCategory = false;
        } else if (category.checked === true) {
            checkboxWarning.innerHTML = "<p class='text-success'>Success!</p>"
            validCategory = true;
            allowSubmission();
        }
    }

    function allowSubmission() {
        (validPostBody && validPostTitle && validCategory) ? document.querySelector('#createPostSubmit').disabled = false :
            document.querySelector('#createPostSubmit').disabled = true;
    }

})();