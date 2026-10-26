import { useState, useEffect } from 'react'
import type { ChangeEvent, SubmitEvent } from 'react'
import { useNavigate, useParams } from 'react-router'
import styles from './EditCar.module.css'

const emptyForm = {
	make: '',
	model: '',
	year: '',
	description: '',
	price: '',
	imageUrl: ''
}

const EditCar = () => {

	const [form, setForm] = useState(emptyForm)
	const [imageFile, setImageFile] = useState<File | null>(null)
	const navigate = useNavigate()
	const { id } = useParams()

	useEffect(() => {
		fetch(`/api/cars/${id}`)
		.then(response => response.json())
		.then(data => setForm({
				make: data.make,
				model: data.model, 
				year: String(data.year),
				description: data.description, 
				price: String(data.price),
				imageUrl: data.imageUrl
			})
		)
	}, [id])

	const handleChange = (e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
		setForm({ ...form, [e.target.name]: e.target.value })
	}

	const handleFileChange = (e: ChangeEvent<HTMLInputElement>) => {
		// Even with a single-file input, the browser always gives you a list, so you take element 0.
		// Without the ?, if files were null, files[0] would throw "Cannot read properties of null".
		setImageFile(e.target.files?.[0] ?? null)
	}

	const handleSubmit = async (e: SubmitEvent<HTMLFormElement>) => {
		
		e.preventDefault()

		const car = { ...form, year: Number(form.year), price: Number(form.price) }

		if (imageFile){

			// FormData is a container for key/value pairs that mimics what an HTML <form> submits. Its main advantage is that values can be files as well as text.
			// 'imageFile' matches the request param in the ImageController
			const formData = new FormData()
			formData.append('imageFile', imageFile)

			const uploadResponse = await fetch('/api/images', {
				method: 'POST',
				body: formData,
			})

			if (!uploadResponse.ok) return

			const { imageUrl } = await uploadResponse.json()

			car.imageUrl = imageUrl
		}

		const response = await fetch(`/api/cars/${id}`, {
			method: 'PUT',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify(car),
		})

		if (response.ok) {
			navigate('/')
		}
	}
	
	const handleDelete = async () => {

		if (!window.confirm('Deleting is permanent. \nConfirm to delete this car?')) return
		
		const response = await fetch(`/api/cars/${id}`, {
			method: 'DELETE',
		})

		if (response.ok) {
			navigate('/')
		}
	}
	
	const handleCancel = () => {
		
		navigate('/')
	}

	return (
		
		<div className={styles['main']}>
      	
			<div className={styles['main-column']}>
		
				<h1>Edit Car</h1>
				
				<form className={styles['edit-car-form']} onSubmit={handleSubmit}>

					<label>
						Make
						<input name="make" value={form.make} onChange={handleChange} required />
					</label>

					<label>
						Model
						<input name="model" value={form.model} onChange={handleChange} required />
					</label>

					<label>
						Year
						<input name="year" type="number" value={form.year} onChange={handleChange} required />
					</label>

					<label>
						Price
						<input name="price" type="number" step="0.01" value={form.price} onChange={handleChange} required />
					</label>


					<label>
						Description
						<textarea name="description" value={form.description} onChange={handleChange} required />
					</label>
					
					<label>
						Image
						{/* accept="image/*" tells the browser which file types the file picker should offer. */}
						<input type="file" accept="image/*" onChange={handleFileChange} />
					</label>

					<button type="button" onClick={handleDelete}>Delete</button>
					<button type="button" onClick={handleCancel}>Cancel</button>
					<button type="submit">Submit</button>
				</form>
			</div>
		</div>
	)
}

export default EditCar