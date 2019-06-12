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

        $.get(href, function(post, status) {
            $('#editId').val(post.id);
            $('#editTitle').val(post.title);
            $('#editBody').val(post.body);
            $('#editFeaturedImg').val(post.featuredImgURL);
        });
        $('#editPostModal').modal();
    });

    // profile page delete post modal

    $('.postDelete').on('click', function(e) {
        e.preventDefault();
        let href = $(this).attr('href');
        $('#delRef').attr('href', href);
        $('#deletePostModal').modal();

    })

    // single post page leave comment modal


});