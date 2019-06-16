"use strict";

(function() {

    //====================
    //                  //
    //   edit post js   //
    //                  //
    //====================

    let validPostTitle = false;
    let validBody = false;

    let editPostTitle = document.querySelector('#editPostTitle');
    let editPostTitleWarning = document.querySelector('#editPostTitleWarning');
    let editPostBody = document.querySelector('#editPostBody');
    let editPostBodyWarning = document.querySelector('#editPostBodyWarning');

    editPostTitle.addEventListener('keyup', declareValidPostTitle);
    editPostTitle.addEventListener('keyup', allowPostSubmission);
    editPostBody.addEventListener('keyup', declareValidBody);
    editPostBody.addEventListener('keyup', allowPostSubmission);

    function declareValidPostTitle() {
        if (editPostTitle.value.length < 1 || editPostTitle.value.length > 20) {
            editPostTitleWarning.innerHTML = "<p class='text-danger'>post title needs to be less than 20 characters.</p>"
            validPostTitle = false;
        } else {
            editPostTitleWarning.innerHTML = "<p class='text-success'>Success!</p>";
            validPostTitle = true;
            allowPostSubmission();
        }
    }

    function declareValidBody() {
        if (editPostBody.value.length < 1) {
            editPostBodyWarning.innerHTML = "<p class='text-danger'>oops! Body cannot be empty.</p>";
            validBody = false;
        } else {
            editPostBodyWarning.innerHTML = "<p class='text-success'>Success!</p>";
            validBody = true;
            allowPostSubmission();
        }
    }

    function allowPostSubmission() {
        (validBody && validPostTitle) ? document.querySelector('#editPostSubmit').disabled = false :
            document.querySelector('#editPostSubmit').disabled = true;
    }
})();
