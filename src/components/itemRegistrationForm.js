import {useState, useEffect} from 'react'
import Button from 'react-bootstrap/Button'
import Form from 'react-bootstrap/Form'
import {Link} from 'react-router-dom'
const ItemForm = ({onRegister,userId}) =>{
    const [description,setDescription]=useState('')
    const [issueStatus,setIssueStatus]=useState('')
    const [itemMake,setItemMake]=useState('')
    const [itemValue,setItemValue]=useState('')
    const [itemType,setItemType]=useState('')
    const [loanId,setLoanId]=useState('')

    const [firstName,setFirstName]=useState('')
    const [dob,setdob] = useState('');
    const [lastName,setLastName]=useState('')
    const [email,setEmail]=useState('')
    const [age,setAge]=useState('')
    const [password,setPassword]=useState('')
    const [errorValues,setErrorValues]=useState({})
    const [submit,setSubmit]=useState(false)
    const [registrationError,setRegistrationError]=useState({})

    const registerItem= async(item)=>{
        console.log(item)
        try{
            const res= await fetch('http://localhost:8082/loanapp/register/item/'+item.loanId,{
                method:'POST',
                body:JSON.stringify(item),
                headers:{
                    'Content-Type':'application/json'
                }
            })
           

            const data = await res.text();
            if(data === "True"){
                alert("True")
                await onRegister()
            }
            else if(data === "False"){
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
            description,issueStatus,itemMake,itemValue,itemType,loanId
        }))
        setSubmit(true)
    }
    const itemMakeValues=['Wooden','Glass','Furniture','Steel','Plastic','Iron','Paper']
    const itemTypeValues=['CAR','FURNITURE','EDUCATION','HOME','MEDICAL','ACCIDENT','PROPERTY']
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
                registerItem({description,issueStatus,itemMake,itemType,itemValue,loanId})
            else
                updateUser({firstName,lastName,"userEmail":email})
            }
    },[errorValues])

    const checkValues=(val)=>{
        const errors={}
        if(!val.description){
            errors.description='Please add some description!'
        }
        if(!val.issueStatus || (!val.issueStatus==='NO' && !val.issueStatus==='YES')){
            console.log(val.issueStatus)

            errors.issueStatus='Issue Status must be Yes or No!'
        }
        else{
            console.log(val.issueStatus)
        }
        if(itemMakeValues.indexOf(val.itemMake)===-1){
            errors.itemMake='Select a valid item make from the drop-down!'
        }
        if(itemTypeValues.indexOf(val.itemType)===-1){
            errors.itemType='Select a valid item type from the drop-down!'
        }
        if(!val.itemValue){
            errors.itemValue='Value is required!'
        }
        if(!val.loanId){
            errors.loanId='Loan Id is required!'
        }
        
        return errors
    }



    return(
        <Form className="reg-form" onSubmit={onSubmit}>
            <Form.Group>
                <Form.Label>Description</Form.Label>
                <Form.Control type="text" placeholder="Add description" value={description} onChange={(e)=>setDescription(e.target.value)}/>
                <p className='form-error'>{errorValues.description}</p>
            </Form.Group>
            <Form.Group>
                <Form.Label>Issue Status</Form.Label>
                <Form.Control as="select" placeholder="Select Issue Status" value={issueStatus} onChange={(e)=>setIssueStatus(e.target.value)}>
                <option value = "">Choose an issue status</option>
                <option value = "YES">YES</option>
                <option value = "NO">NO</option>
                </Form.Control>
                <p className='form-error'>{errorValues.issueStatus}</p>
            </Form.Group>
            <Form.Group>
                <Form.Label>Item Make</Form.Label>
                <Form.Control as="select" placeholder="Select Item Make" value={itemMake} onChange={(e)=>setItemMake(e.target.value)}>
                <option value = "">Choose an item make</option>
                {
                itemMakeValues  && itemMakeValues.map((opt)=>(
                    <option key={opt} value= {opt}>{opt}</option>
                    ))
                }
                </Form.Control>
                <p className='form-error'>{errorValues.itemMake}</p>
            </Form.Group>
            <Form.Group>
                <Form.Label>Value</Form.Label>
                <Form.Control type="number" placeholder="Add Value" value={itemValue} onChange={(e)=>setItemValue(e.target.value)}/>
                <p className='form-error'>{errorValues.itemValue}</p>
            </Form.Group>
            <Form.Group>
                <Form.Label>Type</Form.Label>
                <Form.Control as="select" placeholder="Select Item Type" value={itemType} onChange={(e)=>setItemType(e.target.value)}>
                <option value = "">Choose an item type</option>
                {
                itemTypeValues  && itemTypeValues.map((opt)=>(
                    <option key={opt} value= {opt}>{opt}</option>
                    ))
                }
                </Form.Control>
                <p className='form-error'>{errorValues.itemType}</p>
            </Form.Group>
            
            <Form.Group>
                <Form.Label>Loan Id</Form.Label>
                <Form.Control type="number" placeholder="Enter an approved Loan Id" value={loanId} onChange={(e)=>setLoanId(e.target.value)}/>
                <p className='form-error'>{errorValues.loanId}</p>
            </Form.Group>
            <Button type="submit" >{userId?'Update':'Register'}</Button>
            <Link to='/admin-dashboard'><Button variant="secondary" className="go-back">DashBoard</Button></Link>

        </Form>
    )
}

export default ItemForm