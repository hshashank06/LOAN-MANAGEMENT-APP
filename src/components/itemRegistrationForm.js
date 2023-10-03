import {useState, useEffect} from 'react'
import Button from 'react-bootstrap/Button'
import Form from 'react-bootstrap/Form'
import {Link} from 'react-router-dom'
import PopupModal from './popupModal'
const ItemForm = ({onRegister,itemId}) =>{
    const [description,setDescription]=useState('')
    const [issueStatus,setIssueStatus]=useState('')
    const [itemMake,setItemMake]=useState('')
    const [itemValue,setItemValue]=useState('')
    const [itemType,setItemType]=useState('')
    const [loanId,setLoanId]=useState('')
    const [loanIdOptions,setLoanIdOptions]=useState([])
    const [errorValues,setErrorValues]=useState({})
    const [submit,setSubmit]=useState(false)
    const [registrationError,setRegistrationError]=useState({})
    const [show, setShow] = useState(false);
    const [popupHeading,setHeading]=useState('')
    const [popupBody,setBody]=useState('')
    

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    let approvedLoans=[]
    useEffect(()=>{
        const getApprovedLoans=async()=>{
            try{
                const url="http://localhost:8082/loanapp/display/loan/all"
                let options={
                    method:'GET'
                }
                const res=await fetch(url,options)
                const data=await res.json()
                approvedLoans=data.filter((el)=>el.loan.status==='YES')
                console.log(approvedLoans)
                let approvedLoanIds=[]
                approvedLoans.forEach((el)=>{
                    approvedLoanIds.push(el.loan.loanId)
                })
                console.log(approvedLoanIds)
                setLoanIdOptions(approvedLoanIds)
                // setUserValues(desiredUser)
                
            }
            catch(e){
                console.log(e)
            }
            
        }
        
            getApprovedLoans()
            // c
    },[]) 
    
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
                setHeading('Success !!')
                setBody('Item has been registered')
                setShow(true)
                await onRegister()
            }
            else if(data === "False"){
                setHeading('Failed !!')
                setBody('Please check the data entered and retry')
                setShow(true)
            }

        }
        catch(e){
            // console.log(e)
            setRegistrationError(e)
            console.log(registrationError)
            setHeading('Failed !!')
                setBody('Please check the data entered and retry')
                setShow(true)
            
        }
    }


    const updateItem= async(item)=>{
        console.log(item)
        try{
            const res= await fetch('http://localhost:8082/loanapp/update/item/'+itemId,{
                method:'POST',
                mode:'cors',
                body:JSON.stringify(item),
                headers:{
                    'Content-Type':'application/json'
                }
            })
           

            const data = await res.text();
            if(data === "Item fields updated"){
                setHeading('Success !!')
                setBody('Item has been updated')
                setShow(true)
                await onRegister()
            }
            else{
                setHeading('Failed !!')
                setBody('Item could not be updated')
                setShow(true)
            }

        }
        catch(e){
            // console.log(e)
            setRegistrationError(e)
            console.log(registrationError)
            setHeading('Failed !!')
                setBody('Item could not be updated')
                setShow(true)
            
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
                const url="http://localhost:8082/loanapp/display/items"
                let options={
                    method:'GET'
                }
                const res=await fetch(url,options)
                const data=await res.json()
                const desiredItem=data.find((el)=>el.itemId==itemId)
                console.log(desiredItem)
                // setUserValues(desiredUser)
                console.log(data)
                setDescription(desiredItem.description)
                setIssueStatus(desiredItem.issueStatus)
                setItemMake(desiredItem.itemMake)
                setItemValue(desiredItem.itemValue)
                setItemType(desiredItem.itemType)
                setLoanId(desiredItem.loanId)
            }
            catch(e){
                console.log(e)
            }
            
        }
        if(itemId){
            console.log("lol")
            displayData()
            // console.log(userValues)
        }
    },[]) 

    useEffect(()=>{
        if(Object.keys(errorValues).length===0 && submit){  
            if(!itemId)
                registerItem({description,issueStatus,itemMake,itemType,itemValue,loanId})
            else
                updateItem({description,issueStatus,itemMake,itemType,'itemValue':parseInt(itemValue)})
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
        console.log(loanIdOptions)
        if(!itemId && loanIdOptions.indexOf(parseInt(val.loanId))===-1){
            errors.loanId='Select an approved loan from the drop-down!'
        }
        
        return errors
    }



    return(
        <div>
            <PopupModal show={show} heading={popupHeading} body={popupBody} handleClose={handleClose}/>
        
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
                <Form.Control as="select" placeholder="Enter an approved Loan Id" disabled={itemId?true:false} value={loanId} onChange={(e)=>setLoanId(e.target.value)}>
                <option value = "">Choose an approved loan</option>
                {
                loanIdOptions  && loanIdOptions.map((opt)=>(
                    <option key={opt} value= {opt}>{opt}</option>
                    ))
                }   
                </Form.Control>
                <p className='form-error'>{errorValues.loanId}</p>
            </Form.Group>
            <Button type="submit" >{itemId?'Update':'Register'}</Button>
            <Link to='/admin-dashboard'><Button variant="secondary" className="go-back">DashBoard</Button></Link>

        </Form>
        <br></br>
        </div>
    )
}

export default ItemForm