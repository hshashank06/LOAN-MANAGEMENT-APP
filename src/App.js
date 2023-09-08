// import logo from './logo.svg';
import './App.css';
import RegistrationForm from './components/registrationForm';
import {BrowserRouter as Router, Route, Routes, Link} from 'react-router-dom'
function App() {

  const registerUser=(user)=>{
    console.log(user)
  }

  return (
    <Router>
      <div className="App">
      <h1>LOAN APP</h1>
      <Routes>
        <Route path='/' element={
          <>
          <Link to='/register'><button>Register</button></Link>
          </>
        } />
        <Route path='/register' element={<RegistrationForm onRegister={registerUser}/>} />
      </Routes>

      
    </div>
    </Router>
    
  );
}

export default App;