let isEditingPost = false;

function startPostEditing(postElement) {
    isEditingPost = true;
    const text = postElement.querySelector(".post-text");
    const textarea = postElement.querySelector("#edit-post-area");
    const editPostForm = postElement.querySelector("#edit-post-form");
    console.log("start");
    text.style = "display: none";
    editPostForm.style = "display: block";
    textarea.textContent = text.textContent;
    textarea.focus();
    textarea.selectionStart = textarea.textContent.length;
}

const editPostButtons = document.querySelectorAll(".edit-post");
editPostButtons.forEach(button =>
    button.addEventListener("click", event => {
        const postElement = event.target.parentNode.parentNode.parentNode;
        if(!isEditingPost) startPostEditing(postElement);
    })
);