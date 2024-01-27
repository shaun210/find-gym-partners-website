import React from "react";
import { useState } from "react";
import "./SignIn.css";
const SignIn = () => {

    const [formData, setFormData] = useState({
        username: '',
        password: '',
        email: '',
        firstName: '',
        lastName: '',
        personalDescription: '',
        gymLevel: "BEGINNER", 
        age: 0,
        yearsOfExperience: 0,
        facebookLink: '',
        instagramLink: '',
        snapchatLink: '',
        tiktokLink: '',
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(formData);
    }

    return (
        <div className="parentBlockSignIn">
        <h1>Sign In</h1>
        <form onSubmit={handleSubmit}>
            <label>
            Email:
            <input type="text" name="email" value={formData.email} onChange={handleChange} />
            </label>
            <label>
            First Name:
            <input type="text" name="firstName" value={formData.firstName} onChange={handleChange} />
            </label>
            <label>
            Last Name:
            <input type="text" name="lastName" value={formData.lastName} onChange={handleChange} />
            </label>
            <label>
            Username:
            <input type="text" name="username" value={formData.username} onChange={handleChange} />
            </label>
            <label>
            Password:
            <input type="password" name="password" value={formData.password} onChange={handleChange} />
            </label>
            <label>
            Gym Level:
            <input type="number" name="gymLevel" value={formData.gymLevel} onChange={handleChange} />
            </label>
            <label>
            Facebook Link:
            <input type="text" name="facebookLink" value={formData.facebookLink} onChange={handleChange} />
            </label>
            <label>
            Instagram Link:
            <input type="text" name="instagramLink" value={formData.instagramLink} onChange={handleChange} />
            </label>
            <label>
            Snapchat Link:
            <input type="text" name="snapchatLink" value={formData.snapchatLink} onChange={handleChange} />
            </label>
            <label>
            TikTok Link:
            <input type="text" name="tiktokLink" value={formData.tiktokLink} onChange={handleChange} />
            </label>
            <label>
            Personal Description:
            <textarea name="personalDescription" value={formData.personalDescription} onChange={handleChange} />
            </label>
            <label>
            What do you think is your current gym level:
            <select name="gymLevel" value={formData.gymLevel} onChange={handleChange}>
                <option value="BEGINNER">Beginner</option>
                <option value="INTERMEDIATE">Intermediate</option>
                <option value="ADVANCED">Advanced</option>
            </select>
            </label>
            <button type="submit">Submit</button>
        </form>
        </div>


    );
}

export default SignIn;