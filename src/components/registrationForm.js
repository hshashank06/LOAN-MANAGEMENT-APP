import {useState} from 'react'

const RegistrationForm = ({onRegister}) =>{
    const [firstName,setFirstName]=useState('')
    const [lastName,setLastName]=useState('')
    const [email,setEmail]=useState('')
    const [age,setAge]=useState('')
    const [userName,setUserName]=useState('')
    const [password,setPassword]=useState('')

    const onSubmit=(e)=>{
        e.preventDefault()
         if(!firstName){
            alert('Add a First Name')
            return
        }
         if(!lastName){
            alert('Add a Last Name')
            return
         }
         if(!email){
            alert('Add an Email')
            return
        }
         if(!age){
            alert('Add your Age')
            return
         }
         if(!userName){
            alert('Add a username')
            return
         }
         if(!password){
            alert('Add a password')
            return
         }
         onRegister({firstName,lastName,email,age,userName,password})

    }



    return(
        <form className="reg-form" onSubmit={onSubmit}>
            <div className="form-control">
                <label>First Name</label>
                <input type="text" placeholder="Add First Name" value={firstName} onChange={(e)=>setFirstName(e.target.value)}/>
            </div>
            <div className="form-control">
                <label>Last Name</label>
                <input type="text" placeholder="Add Last Name" value={lastName} onChange={(e)=>setLastName(e.target.value)}/>
            </div>
            <div className="form-control">
                <label>Email Id</label>
                <input type="text" placeholder="Add Email Id" value={email} onChange={(e)=>setEmail(e.target.value)}/>
            </div>
            <div className="form-control">
                <label>Age</label>
                <input type="text" placeholder="Add Age" value={age} onChange={(e)=>setAge(e.target.value)}/>
            </div>
            <div className="form-control">
                <label>Username</label>
                <input type="text" placeholder="Add Username" value={userName} onChange={(e)=>setUserName(e.target.value)}/>
            </div>
            <div className="form-control">
                <label>Password</label>
                <input type="text" placeholder="Add Password" value={password} onChange={(e)=>setPassword(e.target.value)}/>
            </div>
            <input type="submit" value="Register" className='btn btn-block' />


        </form>
    )
}

export default RegistrationForm