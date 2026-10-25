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
        <li key={car.id}>{car.year} {car.make} {car.model} <img src={car.imageUrl} alt="car-picture" /></li>
      ))}
    </ul>
  )
}

export default Home