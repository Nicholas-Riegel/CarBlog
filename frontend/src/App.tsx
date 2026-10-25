import { Route, Routes } from 'react-router'
import Nav from './components/Nav'
import Home from './components/Home'
import AddCar from './components/AddCar'

import './App.css'

function App() {

  return (
    <>
      <Nav />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/add-car" element={<AddCar />} />
      </Routes>
    </>
  )
}

export default App
