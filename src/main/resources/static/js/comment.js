function editComment(button) {
    const commentId = button.getAttribute('data-id');
    const commentElement = document.getElementById('comment-' + commentId);
    const commentContent = commentElement.textContent;

    const newContent = prompt('Edit your comment:', commentContent);
    if (newContent) {
        // Save new content to server here, then update commentElement.textContent
    }
}