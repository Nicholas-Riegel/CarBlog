import { useEffect, useState } from 'react'

import './App.css'

type Car = {
  id: number
  make: string
  model: string
  year: number
  description: string
  price: number
  imageUrl: string
}

function App() {

  const [cars, setCars] = useState<Car[]>([])

  useEffect(() => {
    fetch('/api/cars')
      .then(response => response.json())
      .then(data => setCars(data))
  }, [])

  return (
    <ul>
      {cars.map(car => (
        <li key={car.id}>{car.year} {car.make} {car.model}</li>
      ))}
    </ul>
  )
}

export default App
