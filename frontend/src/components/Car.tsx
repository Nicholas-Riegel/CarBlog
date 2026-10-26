import { useState, useEffect } from 'react'
import { useNavigate, useParams } from 'react-router'
import type { CarType } from '../types'
import styles from './Car.module.css'


const Car = () => {

	const [car, setCar] = useState<CarType | null>(null)
	const { id } = useParams()
    const navigate = useNavigate()

	useEffect(() => {
		fetch(`/api/cars/${id}`)
		.then(response => response.json())
		.then(data => setCar(data))
	}, [id])

    if (!car) return <p>Loading...</p>

	return (
		
		<div className={styles['main']}>
			<div className={styles['main-column']}>
                <h3>{car.make} {car.model}</h3>
                <img src={car.imageUrl} alt="individual-car" />
                <p>{car.description}</p>
                <button onClick={() => navigate(`/edit-car/${id}`)}>Edit</button>
			</div>
		</div>
	)
}

export default Car