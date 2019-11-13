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
                name: '',
                post:'',
                city:''
                
            },
            UserPost:[]
           
        }
        this.componentDidMount=this.componentDidMount.bind(this);
        this.handleFormSubmit=this.handleFormSubmit.bind(this);
        this.handleBlur = this.handleBlur.bind(this);
        this.handleClearForm = this.handleClearForm.bind(this);
        this.handleInput = this.handleInput.bind(this);
         this.handleInputPost = this.handleInputPost.bind(this);
         this.handleInputCity = this.handleInputCity.bind(this);
        
    }
    componentDidMount(e) {
        let apiURL = "http://localhost:8080/restfulservice/rest/usersubmissions/";        
        fetch(apiURL).then(response => {
            response.json().then(data => {
                console.log("Successful" + data.submission);
                this.setState({ UserPost: data })
            })
        })
    }
   

    /* This lifecycle hook gets executed when the component mounts */

    handleBlur(e) {
        let apiURL = "http://localhost:8080/restfulservice/rest/usersubmissions?userName=" + this.state.newUser.name;
        fetch(apiURL).then(response => {
            response.json().then(data => {
                console.log("Successful hello" + data.submissionDate);
                this.setState({ UserPost: data })
            })
        })
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

    handleInputPost(e) {
        let value = e.target.value;
        let post = e.target.name;
        this.setState(prevState => ({
            newUser:
            {
                ...prevState.newUser, [post]: value
            }
        }), () => console.log(this.state.newUser))
    }

    handleInputCity(e) {
        let value = e.target.value;
        let city = e.target.name;
        this.setState(prevState => ({
            newUser:
            {
                ...prevState.newUser, [city]: value
            }
        }), () => console.log(this.state.newUser))
    }

    

    handleFormSubmit(e) {
        e.preventDefault();
        let userData = this.state.newUser;
        let apiURL="http://localhost:8080/restfulservice/rest/usersubmissions/";
        console.log("User entered" +userData.name);
        console.log("User entered" + userData.post);
        fetch(apiURL,{
            method:'POST',
            body: JSON.stringify({
                'userName': userData.name,
                'submission': userData.post,
                'city':userData.city,
                'submissionId':null,
                'submissionDate':null
            }),
            headers:{
                'Accept':'application/json',
                'Content-Type':'application/json'
            }

        }).then(response => {
            console.log("Successful testing" );
            let apiURL = "http://localhost:8080/restfulservice/rest/usersubmissions/";
            fetch(apiURL).then(response => {
                response.json().then(data => {
                    console.log("Successful" + data.submission);
                    this.setState({ UserPost: data })
                })
            })
        })
    }

    handleClearForm(e) {

        e.preventDefault();
        this.setState({
            newUser: {
                name: '',
                post:'',
                city:''
            } 
        })
    }

    render() {
        return (

            <form className="container-fluid" onSubmit={this.handleFormSubmit}>

                <Input inputType={'text'}
                    title={'User Name'}
                    name={'name'}
                    value={this.state.newUser.name}
                    placeholder={'Enter Your UserName'}
                    handleChange={this.handleInput}
                    onBlur={this.handleBlur}

                /> {/* Name of the user */}
                <Input inputType={'text'}
                    title={'User Post '}
                    name={'post'}
                    value={this.state.newUser.post}
                    placeholder={'Submit your post'}
                    handleChange={this.handleInputPost}

                /> {/* Name of the user */}
                <Input inputType={'text'}
                    title={'User City'}
                    name={'city'}
                    value={this.state.newUser.city}
                    placeholder={'Enter your City'}
                    handleChange={this.handleInputCity}
                    
                /> {/* Name of the user */}
     
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
                <UserPost UserPost={this.state.UserPost} />

            </form>

        );
    }
}

const buttonStyle = {
    margin: '10px 10px 10px 10px'
}

export default FormContainer;