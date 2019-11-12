import React, { Component }  from 'react'
import Button from '../components/Button';

const UserPost = ({ UserPost }) => {
    return (
        <div>
            <h1>Submission List</h1>
            {UserPost.map((UserPost) => (
                <div>
                    <h1>{UserPost.userName}</h1>
                    <div>
                        <h3>
                            <p style={postStyle}> {UserPost.submission}-{UserPost.submissionDate} 
                        <Button
                            type={'primary'}
                            title={'Reply'}
                            style={buttonStyle}
                        /> </p></h3>
                        <p style={responseStyle}>{UserPost.postResponses} </p>           
                        
                    </div>
                </div>
            ))}
        </div>
    )
}

const buttonStyle = {
    margin: '10px 10px 10px 10px'
}
const postStyle = {
    fontSize: '15px',
    textIndent:'35px'
};
const responseStyle = {
    fontSize: '15px',
    textIndent: '65px'
};

export default UserPost;
