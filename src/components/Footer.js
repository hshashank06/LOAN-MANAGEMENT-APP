import React from "react"

const Footer = () => <footer className="footer-section"style={{}}>
    <div className=" text-md-left">
        <div className="row" style={{marginLeft:100}}>
            <div className="col-md-4 mb-md-0 mb-3">
                <h5 className="foot-text  " >Laxmi Chit Funds </h5>
                <p className="foot-text  " > Since 1987 </p>
            </div>

            {/* <hr className="clearfix w-100 d-md-none pb-0"/> */}

            <div className="col-md-4 mb-md-0 mb-3">
                <h5 className=" foot-text">Privacy and Security</h5>
                <ul className="list-unstyled foot-text">
                    <li><a href="/privacypolicies" className="foot-text">Privacy Policy</a></li>
                    <li><a href="/copyright" className="foot-text">Copyright</a></li>
                    {/* <li><a href="#!">Link 3</a></li>
                    <li><a href="#!">Link 4</a></li> */}
                </ul>
            </div>

            <div className="col-md-3 mb-md-0 mb-3">
                <h5 className=" foot-text">About</h5>
                <ul className="list-unstyled foot-text">
                    <li><a href="/frequent-questions" className="foot-text">Frequently Asked Questions</a></li>
                    <li><a href="#!" className="foot-text">Contact</a></li>
                    {/* <li><a href="#!" className="foot-text">Location</a></li> */}
                    {/* <li><a href="#!"></a></li> */}
                </ul>
            </div>
        </div>
    </div>

    <div className="footer-copyright text-center  foot-text">© 2023 Copyright:
        <a href="/" className="foot-text"> LaxmiFunds</a>
    </div>

</footer>

export default Footer