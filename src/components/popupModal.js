import { useState } from "react"
import  Modal  from "react-bootstrap/Modal"
import  Button  from "react-bootstrap/Button"
const PopupModal=({show,heading,body,handleClose})=>{
    return(
        <>
        <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
            <Modal.Title>{heading}</Modal.Title>
            </Modal.Header>
            <Modal.Body>{body}</Modal.Body>
            <Modal.Footer>
            {/* <Button variant="secondary" onClick={handleClose}>
                Close
            </Button> */}
            <Button variant="primary" onClick={handleClose}>
                Close
            </Button>
            </Modal.Footer>
           </Modal>
        </>
    )
}

export default PopupModal