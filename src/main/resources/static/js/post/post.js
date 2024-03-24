
const commentsPerPage = 5; // 한 페이지에 보일 댓글 수
let currentPage = 1; // 현재 페이지

// 수정 기능
function editComment(index) {
    const email = document.getElementById('username').textContent.trim();

    console.log("editComment : " + posts[index].email + " email : " + email)

    if (email == posts[index].email) {
        const commentElement = document.querySelector('.comment:nth-child(' + (index + 1) + ')');

        // 이미 수정 폼이 열려 있는지 확인
        if (commentElement.querySelector('.edit-form')) {
            return; // 수정 폼이 이미 열려있으면 더 이상 처리하지 않음
        }

        const commentContent = commentElement.querySelector('.comment-content');
        const originalContent = commentContent.querySelector('p').textContent;

        // 원래 내용 보이기
        commentContent.style.display = 'block';

        const onlyContent = originalContent.split(":")[1].trim() // 게시글 내용만 

        // 수정 폼 추가
        const editForm = document.createElement('div');
        editForm.classList.add('edit-form');
        editForm.innerHTML = `<textarea class="edit-content" maxlength="500">${onlyContent}</textarea>
                                <div class="button-container">
                                    <button onclick="saveEdit(${index})">저장</button>
                                    <button onclick="cancelEdit(${index})">취소</button>
                                </div>`;
        commentElement.appendChild(editForm);

    }
}

// 수정 API
function saveEditApi(index, editedContent) {

    console.log("saveEditApi [info] : " + posts[index].email)
    // 서버로 수정된 내용을 전송
    console.log("saveEditApi index : " + index + " " + posts[index].postId, posts[index].email)
    fetch(`/api/v1/post/update/${postIDList[index]}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            postId: postIDList[index],
            content: editedContent,
            email: posts[index].email
        })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('게시물 업데이트 실패');
            }
            return response.json();
        })
        .then(data => {
            console.log('게시물이 성공적으로 업데이트되었습니다:', data);
            // 업데이트된 내용을 화면에 반영하는 등의 작업 수행
        })
        .catch(error => {
            console.error('게시물 업데이트 중 오류 발생:', error);
        });
}



function deleteComment(index) {

    const email = document.getElementById('username').textContent.trim();
    console.log("email : " + email);
    console.log("posts[index] content : " + posts[index].content);
    console.log("posts[index] postId : " + posts[index].postId);

    if (posts[index].email == email) {
        const confirmation = confirm("정말로 이 댓글을 삭제하시겠습니까?");
        if (confirmation) {


            posts.splice(index, 1); // 리스트에서 삭제 
            deletePostApi(index);
            displayComments();


        }
    }
}

function deletePostApi(index) {
    console.log("postIDList : " + postIDList[index]);
    console.log(`/api/v1/post/delete/${postIDList[index]}`)

    fetch(`/api/v1/post/delete/${postIDList[index]}`, {
        method: 'delete',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            postIDList.splice(index, 1);

            if (!response.ok) {

                throw new Error('게시물 삭제 실패');
            }
            return response.json();
        })
        .then(data => {
            console.log('게시물이 성공적으로 삭제되었습니다:', data);
        })
        .catch(error => {
            console.error('게시물 삭제 중 오류 발생:', error);
        });
}





// 댓글 목록 및 페이지 링크를 표시하는 함수
function displayComments() {
    const commentList = document.querySelector('.comments');
    const pagination = document.querySelector('.pagination');

    commentList.innerHTML = '';
    pagination.innerHTML = '';

    const startIndex = (currentPage - 1) * commentsPerPage;
    const endIndex = startIndex + commentsPerPage;
    const displayedComments = posts.slice(startIndex, endIndex);

    displayedComments.forEach((comment, index) => {
        const commentElement = document.createElement('div');
        commentElement.classList.add('comment');
        commentElement.innerHTML = `<div class="comment-content">
                    <p><span class="comment-author">${comment.author}:</span> ${comment.content}</p>
                </div>
                <div class="comment-buttons">
                    <button onclick="editComment(${startIndex + index})">수정</button>
                    <button onclick="deleteComment(${startIndex + index})">삭제</button>
                </div>
            `;
        commentList.appendChild(commentElement);
    });

    const totalPages = Math.ceil(posts.length / commentsPerPage);
    for (let i = 1; i <= totalPages; i++) {
        const pageLink = document.createElement('a');
        pageLink.classList.add('page-link');
        pageLink.textContent = i;
        pageLink.addEventListener('click', () => {
            currentPage = i;
            displayComments();
        });
        pagination.appendChild(pageLink);
    }
}

let postIDList = [];

// 페이지 로드 시 게시글 표시
fetch('/api/v1/post/all')
    .then(response => response.json())
    .then(data => {
        // 서버에서 받은 데이터를 변수에 할당
        console.log("data type: " + typeof data + ", data: ", data);
        posts = data;

        console.log("data : " + data);


        // postIDList -> 삭제 수정에 사용 
        data.forEach(post => {
            postIDList.push(post.postId);

        });

        console.log("postIdList : " + postIDList);

        if (data != null) {
            displayComments();
        } // 댓글을 표시하는 함수 호출
    })
    .catch(error => {
        console.error('댓글을 불러오는 동안 오류가 발생했습니다:', error);
    });

// 페이지 로드 시 댓글 표시
displayComments();



function addAdditionalData(event) {

    event.preventDefault(); // 폼 제출 방지


    const username = document.getElementById('username').textContent.trim();
    const author = null;

    const form = event.target;
    console.log("form : " + form);

    // 폼 데이터 준비
    const formData = new FormData(form);
    formData.append('email', username);

    // 서버에 비동기적으로 POST 요청 보내기
    fetch('/api/v1/post/write', {
        method: 'POST',
        body: formData
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('댓글 추가 실패');
            }
            return response.json();
        })
        .then(data => {
            console.log(data)

            data.forEach(post => {
                // post는 Map<String, String> 형식의 데이터
                const email = post.email;
                let author, content, postId;


                try {
                    author = data[0].nickname;
                    postId = data[0].postId; //String -> Long
                } catch (error) {
                    author = null;
                    console.error(error);
                }

                content = post.content;

                // 데이터를 출력하거나 활용하기
                console.log("Author: " + author + ", Content: " + content);

                // 댓글 데이터를 Map 형태로 생성하여 리스트에 추가
                const postData = {
                    email: post.email,
                    author: author,
                    content: content
                };

                postIDList.unshift(postId);


                posts.unshift(postData);

                console.log("new posts : " + posts)
                console.log("new postIDList : " + postIDList)

            });

            displayComments(); // 댓글 표시를 업데이트
            form.reset(); // 폼의 입력 요소를 초기화
        })
        .catch(error => {
            console.error('오류:', error);
        });
}
function saveEdit(index) {
    const email = document.getElementById('username').textContent.trim();

    if (posts[index].email == email) {
        const editedContent = document.querySelector('.comment:nth-child(' + (index + 1) + ') .edit-content').value;
        posts[index].content = editedContent;

        // 수정 완료 후 수정 폼 제거
        const commentElement = document.querySelector('.comment:nth-child(' + (index + 1) + ')');
        commentElement.removeChild(commentElement.querySelector('.edit-form'));
        // 댓글 수정 
        saveEditApi(index, editedContent)
        // 다시 댓글 표시
        displayComments();
    }
}

// 취소 기능
function cancelEdit(index) {
    const commentElement = document.querySelector('.comment:nth-child(' + (index + 1) + ')');
    // 수정 폼 제거
    commentElement.removeChild(commentElement.querySelector('.edit-form'));

    // 원래 내용 보이기
    commentElement.querySelector('.comment-content').style.display = 'block';
}
