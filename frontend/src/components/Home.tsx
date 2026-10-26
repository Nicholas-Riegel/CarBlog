import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router'
import type { Car } from '../types'
import styles from './Home.module.css'

const Home = () => {

  const [cars, setCars] = useState<Car[]>([])
  const navigate = useNavigate()

  useEffect(() => {
    fetch('/api/cars')
      .then(response => response.json())
      .then(data => setCars(data))
  }, [])
  
  return (
    <div className={styles['main']}>
      <div className={styles['main-column']}>

        {cars.map(car => (
          <div key={car.id}>
            <img src={car.imageUrl ? car.imageUrl : undefined} alt="car-picture" />
            {car.year} {car.make} {car.model} 
            <button onClick={() => navigate(`/edit-car/${car.id}`)}>Edit</button>
          </div>
        ))}

      </div>
    </div>
  )
}

export default Home