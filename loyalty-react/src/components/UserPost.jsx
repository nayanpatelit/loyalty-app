import React from 'react'

const UserPost = ({ UserPost }) => {
    return (
        <div>
            <h1>Submission List</h1>
            {UserPost.map((UserPost) => (
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">{UserPost.submission}-{UserPost.submissionDate}</h5>
                    </div>
                </div>
            ))}
        </div>
    )
}
export default UserPost;
