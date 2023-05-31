function editComment(button) {
    const commentId = button.getAttribute('data-id');
    const commentElement = document.getElementById('comment-' + commentId);
    const commentContent = commentElement.textContent;

    const newContent = prompt('Edit your comment:', commentContent);
    if (newContent) {
        $.post(`/board/${id}/comment/${commentId}/update`, {content: newContent}, function(data) {
            window.location.href = data;
        });
    }
}