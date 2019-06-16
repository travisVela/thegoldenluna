"use strict";
$(document).ready(function(){
    //================
    //              //
    //   popovers   //
    //              //
    //================

    $('.popover-target').hover(
        function(e) {
            $('.hover-popover').show();
        },
        function(e) {
            $('.hover-popover').hide();
        });


    //==============
    //            //
    //   modals   //
    //            //
    //==============

    //profile page edit post modal

    $('.postEdit').on('click', function(e) {
        e.preventDefault();
        let href = $(this).attr('href');

        $.get(href, function(post, categories, status) {
            $('#editId').val(post.id);
            $('#editPostTitle').val(post.title);
            $('#editPostBody').val(post.body);
            $('#dateCreated').val(post.dateCreated);
            $('#timeCreated').val(post.timeCreated);
            $('#editFeaturedImg').val(post.featuredImgURL);
            $('')

        });
        $('#editPostModal').modal();
    });

    // profile page delete post modal

    $('.postDelete').on('click', function(e) {
        e.preventDefault();
        let href = $(this).attr('href');
        $('#delRef').attr('href', href);
        $('#deletePostModal').modal();

    });

    // single post page edit comment modal
    $('.commentEdit').on('click', function(e) {
        e.preventDefault();
        let href = $(this).attr('href');

        $.get(href, function(comment, status) {

            $('#editId').val(comment.id);
            $('#editCommentBody').val(comment.body);
            $('#dateCreated').val(comment.dateCreated);
            $('#timeCreated').val(comment.timeCreated);

        });
        $('#editCommentModal').modal();

    });

    // single post page delete comment modal
    $('.commentDelete').on('click', function(e) {
        e.preventDefault()
        let href = $(this).attr('href');
        $('#commentDelRef').attr('href', href);
        $('#deleteCommentModal').modal();
    });

    //   cat card bg generator


    function random_rgb(num) {
        let val = Math.round(Math.random() * num);
        return val;
    }

    $('.cat-card').css('background-color', function(){
        let r = random_rgb(256);
        let g = random_rgb(256);
        let b = random_rgb(256);
        let rgb = 'rgb(' + r + ', ' + g  + ', ' + b + ')';

        console.log("rgb " + rgb)
        return rgb;
    });


});