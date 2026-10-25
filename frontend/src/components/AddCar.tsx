import { useState } from 'react'
import type { ChangeEvent, SubmitEvent } from 'react'
import { useNavigate } from 'react-router'

const emptyForm = {
  make: '',
  model: '',
  year: '',
  description: '',
  price: '',
  imageUrl: '',
}

const AddCar = () => {

  const [form, setForm] = useState(emptyForm)
  const navigate = useNavigate()

  const handleChange = (e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value })
  }

  const handleSubmit = async (e: SubmitEvent<HTMLFormElement>) => {
    e.preventDefault()

    const response = await fetch('/api/cars', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        ...form,
        year: Number(form.year),
        price: Number(form.price),
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

      {/* <label>
        Image URL
        <input name="imageUrl" type="url" value={form.imageUrl} onChange={handleChange} required />
      </label> */}

      <label>
        Description
        <textarea name="description" value={form.description} onChange={handleChange} required />
      </label>

      <button type="submit">Add car</button>
    </form>
  )
}

export default AddCar