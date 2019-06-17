"use strict";

(function(){


    //=======================================
    //                                     //
    //   everything commented out was      //
    //   an attempt to validate dropdown   //
    //                                     //
    //=======================================

    let validPostTitle = false;
    let validPostBody = false;
    // let validCategory = false;
    let submit = document.querySelector('#createPostSubmit').disabled = true;

    let createPostTitle = document.querySelector('#createPostTitle');
    let createPostTitleWarning = document.querySelector('#createPostTitleWarning');
    let createPostBody = document.querySelector('#createPostBody');
    let createPostBodyWarning = document.querySelector('#createPostBodyWarning');
    // let checkboxWarning = document.querySelector('#checkboxWarning');
    // let category = document.querySelector('input[name=checked]');
    // let categories = document.querySelector('#categories');
    let postCats = document.querySelector('#postCats');


    createPostTitle.addEventListener('keyup', declareValidPostTitle);
    createPostTitle.addEventListener('keyup', allowSubmission);
    createPostBody.addEventListener('keyup', declareValidPostBody);
    createPostBody.addEventListener('keyup', allowSubmission);
    // category.addEventListener('click', validateDropdown);
    // category.addEventListener('click', allowSubmission);
    // categories.addEventListener('click', validateDropdown);


    function declareValidPostTitle() {
        if (createPostTitle.value.length < 1 || createPostTitle.value.length > 20) {
            createPostTitleWarning.innerHTML = "<p class='text-danger'>oops! Post title must be no more than 20 characters</p>";
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

    // function validateDropdown() {
    //     let checkedCats = [];
    //     for (let i = 0; i < categories.length; i++) {
    //         if (categories[i].checked) {
    //             checkedCats.push(categories[i]);
    //         }
    //     }
    //     console.log(checkedCats.length);
    //
    //     if (checkedCats < 0) {
    //         checkboxWarning.innerHTML = "<p class='text-danger'>oh? Please choose at least 1 category.</p>";
    //         validCategory = false;
    //     } else if (checkedCats > 0) {
    //         checkboxWarning.innerHTML = "<p class='text-success'>Success!</p>";
    //         validCategory = true;
    //         allowSubmission();
    //     }
    // }
    function allowSubmission() {
        (validPostBody && validPostTitle) ? document.querySelector('#createPostSubmit').disabled = false :
            document.querySelector('#createPostSubmit').disabled = true;
    }


})();