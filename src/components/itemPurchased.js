import  Button  from 'react-bootstrap/Button'
import Table from 'react-bootstrap/Table'
import { useUser } from './userContext';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router';

const ItemPurchased = () => {

    const navigation = useNavigate();
    const [response,setResponse] = useState([]);
    // const {userId} = useUser();
    const userId=localStorage.getItem('userId')

    const setItemsPurchased = async () => {
        const url = `http://localhost:8082/loanapp/display/user/${userId}/items`
        const options = {
            method: 'GET',
            mode: 'cors',

        }

        const resp = await fetch(url,options)
        const data = await resp.json();
        console.log(data)
        setResponse(data);
    }

    const onGoBackButtonClick = () => {
        navigation(-1);
    }

    useEffect(() => {
        setItemsPurchased();
    },[])


    return (
        <>
        <h2 className='sub-heading'>Items Purchased</h2>
        <Table striped bordered hover>
            <thead>
                <tr>
                <th>#</th>
                <th>Item Id</th>
                <th>Description</th>
                <th>Issue Status</th>
                <th>Item Make</th>
                <th>Tem Type</th>
                <th>Item Value</th>
                </tr>
            </thead>
            <tbody>
                {
                    response && response.map((item)=>(
                        <tr key={item.itemId}>
                            <td>{response.indexOf(item)+1}</td>
                            <td>{item.itemId}</td>
                            <td>{item.description}</td>
                            <td>{item.issueStatus}</td>
                            <td>{item.itemMake}</td>
                            <td>{item.itemType}</td>
                            <td>{item.itemValue}</td>
                       </tr>
                    ))
                }
            </tbody>
            </Table>
            <Button type="button" onClick={onGoBackButtonClick}> Go Back </Button>
            </>

    )
    
}

export default ItemPurchased;