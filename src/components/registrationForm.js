import {useState, useEffect} from 'react'
import Button from 'react-bootstrap/Button'
import Form from 'react-bootstrap/Form'
import {Link} from 'react-router-dom'
const RegistrationForm = ({onRegister,userId}) =>{
    const [firstName,setFirstName]=useState('')
    const [dob,setdob] = useState('');
    const [lastName,setLastName]=useState('')
    const [email,setEmail]=useState('')
    const [age,setAge]=useState('')
    const [password,setPassword]=useState('')
    const [errorValues,setErrorValues]=useState({})
    const [submit,setSubmit]=useState(false)
    const [registrationError,setRegistrationError]=useState({})

    const registerUser= async(user)=>{
        console.log(user)
        try{
            const res= await fetch('http://localhost:8082/loanapp/register/user',{
                method:'POST',
                body:JSON.stringify(user),
                headers:{
                    'Content-Type':'application/json'
                }
            })
           

            const data = await res.text();
            if(data === "NEW USER REGISTERED"){
                alert("New User has been Registered")
                await onRegister()
            }
            else if(data === "NEW USER COULLD NOT BE ADDED"){
                alert("User could not be registered")
            }

        }
        catch(e){
            // console.log(e)
            setRegistrationError(e)
            console.log(registrationError)
            
        }
    }


    const updateUser= async(user)=>{
        console.log(user)
        try{
            const res= await fetch('http://localhost:8082/loanapp/update/user/'+userId,{
                method:'POST',
                mode:'cors',
                body:JSON.stringify(user),
                headers:{
                    'Content-Type':'application/json'
                }
            })
           

            const data = await res.text();
            if(data === "The Fields have been updated"){
                alert("User has been Updated")
                await onRegister()
            }
            else{
                alert("User could not be updated")
            }

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
            firstName,lastName,email,age,password
        }))
        setSubmit(true)
    }

    useEffect(()=>{
        const displayData=async()=>{
            try{
                const url="http://localhost:8082/loanapp/get/users"
                let options={
                    method:'GET'
                }
                const res=await fetch(url,options)
                const data=await res.json()
                const desiredUser=data.find((el)=>el.userId==userId)
                console.log(desiredUser)
                // setUserValues(desiredUser)
                console.log(data)
                setFirstName(desiredUser.firstName)
                setLastName(desiredUser.lastName)
                setEmail(desiredUser.userEmail)
                setPassword(desiredUser.userPassword)
                setAge(desiredUser.userAge)
                setdob(desiredUser.userdob)
            }
            catch(e){
                console.log(e)
            }
            
        }
        if(userId){
            console.log("lol")
            displayData()
            // console.log(userValues)
        }
    },[])

    useEffect(()=>{
        if(Object.keys(errorValues).length===0 && submit){  
            if(!userId)
                registerUser({firstName,lastName,"userEmail":email,"userAge":age,"userdob":dob,"userPassword":password})
            else
                updateUser({firstName,lastName,"userEmail":email})
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
        if(!val.password){
            errors.password='Password is required!'
        }
        else if(val.password.length<8 || val.password.length>16){
            errors.password='Password must have atleast 8 characters and atmost 16!'
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
                <Form.Label>Date Of Birth</Form.Label>
                <Form.Control type="date" value={dob} onChange={(e)=>setdob(e.target.value)}/>
            </Form.Group>
            
            <Form.Group>
                <Form.Label>Password</Form.Label>
                <Form.Control type="password" placeholder="Add Password" value={password} disabled={userId?true:false} onChange={(e)=>setPassword(e.target.value)}/>
                <p className='form-error'>{errorValues.password}</p>
            </Form.Group>
            <Button type="submit" >{userId?'Update':'Register'}</Button>
            <Link to='/admin-dashboard'><Button variant="secondary" className="go-back">DashBoard</Button></Link>

        </Form>
    )
}

export default RegistrationForm