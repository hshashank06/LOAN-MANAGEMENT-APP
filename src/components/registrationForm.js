import {useState, useEffect} from 'react'
import Button from 'react-bootstrap/Button'
import Form from 'react-bootstrap/Form'
import {Link} from 'react-router-dom'
import PopupModal from './popupModal'
const RegistrationForm = ({onRegister,userId}) =>{
    const [firstName,setFirstName]=useState('')
    const [dob,setdob] = useState('');
    const [lastName,setLastName]=useState('')
    const [email,setEmail]=useState('')
    const [password,setPassword]=useState('')
    const [errorValues,setErrorValues]=useState({})
    const [submit,setSubmit]=useState(false)
    const [registrationError,setRegistrationError]=useState({})
    const [show, setShow] = useState(false);
    const [popupHeading,setHeading]=useState('')
    const [popupBody,setBody]=useState('')
    

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

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
                // alert("New User has been Registered")
                setHeading('Registration Successful!!')
                setBody('User has been registered!')
                setShow(true)
                await onRegister()
            }
            else if(data === "NEW USER COULLD NOT BE ADDED"){
                setHeading('Registration failed!!')
                setBody('Please check the data entered again!')
                setShow(true)
                // alert("User could not be registered")
            }

        }
        catch(e){
            // console.log(e)
            setRegistrationError(e)
            console.log(registrationError)
            setHeading('Registration failed!!')
            setBody('Please check the data entered again!')
            setShow(true)
            
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
                setHeading('Update Successful!!')
                setBody('User has been updated!')
                setShow(true)
                await onRegister()
            }
            else{
                setHeading('Update failed!!')
            setBody('Please check the data entered again!')
            setShow(true)
            }

        }
        catch(e){
            // console.log(e)
            setRegistrationError(e)
            console.log(registrationError)
            setHeading('Update failed!!')
            setBody('Please check the data entered again!')
            setShow(true)
            
        }
    }

    const onSubmit=(e)=>{
        e.preventDefault()
        setErrorValues(checkValues({
            firstName,lastName,email,dob,password
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
                registerUser({firstName,lastName,"userEmail":email,"userAge":19,"userdob":dob,"userPassword":password})
            else
                updateUser({firstName,lastName,"userEmail":email,"userdob":dob})
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
        console.log(val.dob)

        var bday=dob.split("-");
        console.log(bday)
        var bday_in_milliseconds = new Date(parseInt(bday[0], 10), parseInt(bday[1], 10) - 1 , parseInt(bday[2]), 10).getTime(); //birth-date in milliseconds
        var now = new Date().getTime(); 
        var adult=false
        if(now - bday_in_milliseconds >= 567648000000){ 
            adult=true
        }
        
        if(!val.dob){
            errors.dob='Date of Birth is required!'
        }
        else if(adult===false){
            errors.dob='You should be atleast 18 years old to register!'
        }
        return errors
    }



    return(
        <div>
            
            <PopupModal show={show} heading={popupHeading} body={popupBody} handleClose={handleClose}/>
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
                <Form.Label>Date Of Birth</Form.Label>
                <Form.Control type="date" value={dob} onChange={(e)=>setdob(e.target.value)}/>
                <p className='form-error'>{errorValues.dob}</p>
            </Form.Group>
            
            <Form.Group>
                <Form.Label>Password</Form.Label>
                <Form.Control type="password" placeholder="Add Password" value={password} disabled={userId?true:false} onChange={(e)=>setPassword(e.target.value)}/>
                <p className='form-error'>{errorValues.password}</p>
            </Form.Group>
            <Button type="submit" >{userId?'Update':'Register'}</Button>
            <Link to='/admin-dashboard'><Button variant="secondary" className="go-back">DashBoard</Button></Link>

        </Form>
        </div>
    )
}

export default RegistrationForm