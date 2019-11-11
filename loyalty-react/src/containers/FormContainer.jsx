import React, { Component } from 'react';

/* Import Components */

import Input from '../components/Input';
import Button from '../components/Button';
import UserPost from '../components/UserPost'


class FormContainer extends Component {

     constructor(props) {
        super(props);

        this.state = {
            newUser: {
                name: ''
                
            },
            UserPost:[]

        }
        
        this.handleFullName = this.handleFullName.bind(this);
        this.handleFormSubmit = this.handleFormSubmit.bind(this);
        this.handleClearForm = this.handleClearForm.bind(this);
        this.handleInput = this.handleInput.bind(this);
    }

    /* This lifecycle hook gets executed when the component mounts */

    handleFullName(e) {
        let value = e.target.value;
        this.setState(prevState => ({
            newUser:
            {
                ...prevState.newUser, name: value
            }
        }), () => console.log(this.state.newUser))
    }

    
    handleInput(e) {
        let value = e.target.value;
        let name = e.target.name;
        this.setState(prevState => ({
            newUser:
            {
                ...prevState.newUser, [name]: value
            }
        }), () => console.log(this.state.newUser))
    }

    

    handleFormSubmit(e) {
        e.preventDefault();
        let userData = this.state.newUser;
        let apiURL="http://localhost:8080/restfulservice/rest/usersubmissions/"+userData.name;
        console.log("User entered" +userData.name);
        fetch(apiURL).then(response => {
            response.json().then(data => {
                console.log("Successful" + data.submission);
                this.setState({ UserPost: data.submission })
            })
        })
    }

    handleClearForm(e) {

        e.preventDefault();
        this.setState({
            newUser: {
                name: ''
            },
            UserPost:[]
        })
    }

    render() {
        return (

            <form className="container-fluid" onSubmit={this.handleFormSubmit}>

                <Input inputType={'text'}
                    title={'User Submission'}
                    name={'name'}
                    value={this.state.newUser.name}
                    placeholder={'Submit your comment'}
                    handleChange={this.handleInput}

                /> {/* Name of the user */}

               <UserPost UserPost={this.state.UserPost}/>

                <Button
                    action={this.handleFormSubmit}
                    type={'primary'}
                    title={'Done'}
                    style={buttonStyle}
                /> { /*Submit */}

                <Button
                    action={this.handleClearForm}
                    type={'secondary'}
                    title={'Clear'}
                    style={buttonStyle}
                /> {/* Clear the form */}

            </form>

        );
    }
}

const buttonStyle = {
    margin: '10px 10px 10px 10px'
}

export default FormContainer;