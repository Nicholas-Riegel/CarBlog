import { useState, useEffect } from 'react'
import type { Car } from '../types'

const Home = () => {

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

export default Home