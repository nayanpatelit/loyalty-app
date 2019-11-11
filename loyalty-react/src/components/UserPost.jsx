import React from 'react'

const UserPost = ({ UserPost }) => {
    return (
        <div>
            <h1>Submission List</h1>
            {UserPost.map((UserPost) => (
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">{UserPost.submission}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">{UserPost.submissionDate}</h6>
                    </div>
                </div>
            ))}
        </div>
    )
}
export default UserPost;
