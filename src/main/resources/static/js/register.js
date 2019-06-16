"use strict";

(function() {
    console.log('hello from register.js!');
    let validUsername = false;
    let validEmail = false;
    let validPassword = false;
    let validBio = false;

    let username = document.querySelector('#username');
    let usernameWarning = document.querySelector('#username-warning');
    let email = document.querySelector('#email');
    let emailWarning = document.querySelector('#email-warning');
    let password = document.querySelector('#password');
    let passwordWarning = document.querySelector('#password-warning');
    let bio = document.querySelector('#bio');
    let bioWarning = document.querySelector('#bio-warning');

    username.addEventListener('keyup', checkIfUsernameExists);
    usernameWarning.addEventListener('keydown', checkIfUsernameExists);
    email.addEventListener('keyup', checkIfEmailExists);
    password.addEventListener('keyup', declareValidPassword);
    bio.addEventListener('keyup', declareValidBio);

    username.addEventListener('keyup', allowSubmission);
    username.addEventListener('keydown', allowSubmission);
    email.addEventListener('keyup', allowSubmission);
    password.addEventListener('keyup', allowSubmission);
    bio.addEventListener('keyup', allowSubmission);

    function checkIfUsernameExists() {
        fetch("/api/v1/user/" + username.value).then(response => {
            response.json().then(data => {
                declareValidUsername(data);
            })
        })
    }

    function checkIfEmailExists() {
        fetch("/api/v1/email/" + email.value).then(response => {
            response.json().then( data => {
                declareValidEmail(data);
            })
        })
    }

    function declareValidUsername(data) {
        if (username.value.length < 6  || username.value.length > 20) {
            usernameWarning.innerHTML = "<p class='text-danger'>Username must be between 6 - 20 characters!</p>"
            validUsername = false;
        } else if (data) {
            usernameWarning.innerHTML = "<p class='text-danger'>Username already exists!</p>"
            validUsername = false;
        } else if (!data) {
            usernameWarning.innerHTML = "<p class='text-success'>Success!</p>"
            validUsername = true;
            allowSubmission();
        }
    }

    function declareValidEmail(data) {
        if (!validateEmail(email.value)) {
            emailWarning.innerHTML = "<p class='text-danger'>incorrect email format!</p>"
            validEmail = false;
        } else if (data) {
            emailWarning.innerHTML = "<p class='text-danger'>email already exists!</p>"
            validEmail = false;
        } else if (validateEmail(email.value) && !data) {
            emailWarning.innerHTML = "<p class='text-success'>SUCCESS!</p>"
            validEmail = true;
            allowSubmission();
        }
    }

    function declareValidPassword() {
        if (password.value.length < 6 || password.value.length > 20) {
            passwordWarning.innerHTML = "<p class='text-danger'>password must be between 6 - 20 characters!</p>"
            validPassword = false;
        } else if (password.value.length >= 6 && password.value.length < 20) {
            passwordWarning.innerHTML = "<p class='text-success'>SUCCESS!</p>";
            validPassword = true;
            allowSubmission();
        }
    }

    function declareValidBio() {
        if (bio.value.length < 1) {
            bioWarning.innerHTML = "<p class='text-danger'>ah c'mon. tell us about yourself</p>"
            validBio = false;
        } else if (bio.value != null) {
            bioWarning.innerHTML = "<p class='text-success'>SUCCESS!</p>"
            validBio = true;
            allowSubmission();
        }
    }

    function allowSubmission() {
        if (validUsername && validEmail && validPassword && validBio) {
            document.querySelector('#register-submit').disabled = false;
        } else {
            document.querySelector('#register-submit').disabled = true;
        }

    }

    function validateEmail(email) {
        let re = /\S+@\S+\.\S+/;
        return re.test(email);
    }

})();