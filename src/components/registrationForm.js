import {useState, useEffect} from 'react'
import Button from 'react-bootstrap/Button'
import Form from 'react-bootstrap/Form'
import {Link} from 'react-router-dom'
const RegistrationForm = ({onRegister}) =>{
    const [firstName,setFirstName]=useState('')
    const [lastName,setLastName]=useState('')
    const [email,setEmail]=useState('')
    const [age,setAge]=useState('')
    const [userName,setUserName]=useState('')
    const [password,setPassword]=useState('')
    const [errorValues,setErrorValues]=useState({})
    const [submit,setSubmit]=useState(false)
    const [registrationError,setRegistrationError]=useState({})

    const registerUser= async(user)=>{
        console.log(user)
        try{
            const res= await fetch('/',{
                method:'POST',
                body:JSON.stringify(user),
                headers:{
                    'Content-Type':'application/json'
                }
            })
            if(res.status===200){
                console.log('Registration complete')
                return
            }
            throw await res.json()
        }
        catch(e){
            // console.log(e)
            setRegistrationError(e)
            console.log(registrationError)
        }
    }

    const onSubmit=(e)=>{
        e.preventDefault()
        setErrorValues(checkValues({
            firstName,lastName,email,age,userName,password
        }))
        setSubmit(true)
    }

    useEffect(()=>{
        if(Object.keys(errorValues).length===0 && submit){  
            registerUser({firstName,lastName,"userEmail":email,"userAge":age,userName,"userPassword":password})
        }
    },[errorValues])

    const checkValues=(val)=>{
        const regex=/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/
        const errors={}
        if(!val.firstName){
            errors.firstName='First Name is required!'
        }
        if(!val.lastName){
            errors.lastName='Last Name is required!'
        }
        if(!val.email){
            errors.email='Email is required!'
        }
        else if(!regex.test(val.email)){
            errors.email='Enter valid email format!'
        }
        if(!val.userName){
            errors.userName='Username is required!'
        }
        if(!val.password){
            errors.password='Password is required!'
        }
        else if(val.password.length<4 || val.password.length>10){
            errors.password='Password must have atleast 4 characters and atmost 10!'
        }
        if(!val.age){
            errors.age='Age is required!'
        }
        else if(val.age<18){
            errors.age='You must be 18 years or above to register!'
        }
        return errors
    }



    return(
        <Form className="reg-form" onSubmit={onSubmit}>
            <Form.Group>
                <Form.Label>First Name</Form.Label>
                <Form.Control type="text" placeholder="Add First Name" value={firstName} onChange={(e)=>setFirstName(e.target.value)}/>
                <p className='form-error'>{errorValues.firstName}</p>
            </Form.Group>
            <Form.Group>
                <Form.Label>Last Name</Form.Label>
                <Form.Control type="text" placeholder="Add Last Name" value={lastName} onChange={(e)=>setLastName(e.target.value)}/>
                <p className='form-error'>{errorValues.lastName}</p>
            </Form.Group>
            <Form.Group>
                <Form.Label>Email Id</Form.Label>
                <Form.Control type="text" placeholder="Add Email Id" value={email} onChange={(e)=>setEmail(e.target.value)}/>
                <p className='form-error'>{errorValues.email}</p>
            </Form.Group>
            <Form.Group>
                <Form.Label>Age</Form.Label>
                <Form.Control type="number" placeholder="Add Age" value={age} onChange={(e)=>setAge(e.target.value)}/>
                <p className='form-error'>{errorValues.age}</p>
            </Form.Group>
            <Form.Group>
                <Form.Label>Username</Form.Label>
                <Form.Control type="text" placeholder="Add Username" value={userName} onChange={(e)=>setUserName(e.target.value)}/>
                <p className='form-error'>{errorValues.userName}</p>
            </Form.Group>
            <Form.Group>
                <Form.Label>Password</Form.Label>
                <Form.Control type="password" placeholder="Add Password" value={password} onChange={(e)=>setPassword(e.target.value)}/>
                <p className='form-error'>{errorValues.password}</p>
            </Form.Group>
            <Button type="submit" >Register</Button>
            <Link to='/'><Button variant="secondary" className="go-back">Go Back</Button></Link>

        </Form>
    )
}

export default RegistrationForm