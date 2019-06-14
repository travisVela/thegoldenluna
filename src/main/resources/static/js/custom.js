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
            $('#editTitle').val(post.title);
            $('#editBody').val(post.body);
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
            $('#editBody').val(comment.body);
            $('#dateCreated').val(comment.dateCreated);
            $('#timeCreated').val(comment.timeCreated);

        });
        $('#editCommentModal').modal();

    });

    // single post page delete comment modal
    $('.commentDelete').on('click', function(e) {
        e.preventDefault()
        let href = $(this).attr('href');
        $('#commentDelRef').attr('href', href)
        $('#deleteCommentModal').modal();
    });


});