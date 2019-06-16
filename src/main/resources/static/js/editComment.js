"use strict";
(function() {

    //=======================
    //                     //
    //   edit comment js   //
    //                     //
    //=======================

    let validCommentBody = false;

    let editCommentBody = document.querySelector('#editCommentBody');
    let editCommentBodyWarning = document.querySelector('#editCommentBodyWarning');


    editCommentBody.addEventListener('keyup', declareValidCommentBody);
    editCommentBody.addEventListener('keyup', allowCommentSubmission);

    function declareValidCommentBody() {
        if (editCommentBody.value.length < 1) {
            editCommentBodyWarning.innerHTML = "<p class='text-danger'>oops! Comment body cannot be empty.</p>"
            validCommentBody = false;
        } else {
            editCommentBodyWarning.innerHTML = "<p class='text-success'>Success!</p>";
            validCommentBody = true;
            allowCommentSubmission();
        }
    }

    function allowCommentSubmission() {
        (validCommentBody )  ? document.querySelector('#editCommentSubmit').disabled = false : document.querySelector('#editCommentSubmit').disabled = true;
    }

})();