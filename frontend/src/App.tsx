import { Route, Routes } from 'react-router'
import Nav from './components/Nav'
import Home from './components/Home'
import AddCar from './components/AddCar'
import EditCar from './components/EditCar'

function App() {

  return (
    <>
      <Nav />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/add-car" element={<AddCar />} />
        <Route path="/edit-car/:id" element={<EditCar />} />
      </Routes>
    </>
  )
}

export default App
