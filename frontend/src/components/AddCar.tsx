import { useState } from 'react'
import type { ChangeEvent, SubmitEvent } from 'react'
import { useNavigate } from 'react-router'

const emptyForm = {
	make: '',
	model: '',
	year: '',
	description: '',
	price: '',
}

const AddCar = () => {

	const [form, setForm] = useState(emptyForm)
	const [imageFile, setImageFile] = useState<File | null>(null)
	const navigate = useNavigate()

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

		if (!imageFile) return

		// FormData is a container for key/value pairs that mimics what an HTML <form> submits. Its main advantage is that values can be files as well as text.
		const formData = new FormData()
		// 'imageFile' matches the request param in the ImageController
		formData.append('imageFile', imageFile)

		const uploadResponse = await fetch('/api/images', {
			method: 'POST',
			body: formData,
		})

		if (!uploadResponse.ok) return

		const { imageUrl } = await uploadResponse.json()

		const response = await fetch('/api/cars', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				...form,
				year: Number(form.year),
				price: Number(form.price),
				imageUrl,
			}),
		})

		if (response.ok) {
			navigate('/')
		}
	}

	return (
		<form onSubmit={handleSubmit}>
		<h1>Add car</h1>

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
			Image
			{/* accept="image/*" tells the browser which file types the file picker should offer. */}
			<input type="file" accept="image/*" onChange={handleFileChange} required />
		</label>

		<label>
			Description
			<textarea name="description" value={form.description} onChange={handleChange} required />
		</label>

		<button type="submit">Add car</button>
		</form>
	)
}

export default AddCar