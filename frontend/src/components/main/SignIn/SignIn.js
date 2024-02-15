import React from "react";
import { useState } from "react";
import "./SignIn.css";
import { paths, listOfAddress } from "../../../constants";
import { signin } from "../../../api/MemberApi";
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
        addressTown: '',
        addressProvince: '',
        addressCountry: 'Canada',
        gender: 'MALE',
        // profilePicture: null,
    });

    const handleChange = (e) => {
        const { name, value, type } = e.target;
        if (type === 'file') {
            setFormData({ ...formData, [name]: e.target.files[0] });
        } else {
            setFormData({ ...formData, [name]: value });
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!formData.email || !formData.addressProvince || !formData.addressTown || !formData.username || !formData.password) {
            window.alert('Please fill in all required fields.');
            return;
        }
        // console.log(formData);
        signin(formData, successSignInCallback, failSignInCallback);
    }

    const successSignInCallback = (data) => {
        localStorage.setItem('member', JSON.stringify(data));
        window.location.href = paths.FRIEND_LIST;
    };

    const failSignInCallback = (data) => {
        window.alert(data);
    };

    
    return (
        <div className="parentBlockSignIn">
          <h1>Sign In</h1>
          <form onSubmit={handleSubmit} className="signInForm">
            <label className="formLabel">
              Email:
              <input type="text" name="email" value={formData.email} onChange={handleChange} className="formInput" />
            </label>
            <label className="formLabel">
              First Name:
              <input type="text" name="firstName" value={formData.firstName} onChange={handleChange} className="formInput" />
            </label>
            <label className="formLabel">
              Last Name:
              <input type="text" name="lastName" value={formData.lastName} onChange={handleChange} className="formInput" />
            </label>
            <label className="formLabel">
              Gender:
              <select name="gender" value={formData.gender} onChange={handleChange} className="formInput">
                <option value="MALE">Male</option>
                <option value="FEMALE">Female</option>
                <option value="OTHER">Other</option>
              </select>
            </label>
            <label className="formLabel">
              Username:
              <input type="text" name="username" value={formData.username} onChange={handleChange} className="formInput" />
            </label>
            <label className="formLabel">
              Password:
              <input type="password" name="password" value={formData.password} onChange={handleChange} className="formInput" />
            </label>
            <label className="formLabel">
              Age:
              <input type="number" name="age" value={formData.age} onChange={handleChange} min="0" className="formInput" />
            </label>
            <label className="formLabel">
              Years of Experience:
              <input type="number" name="yearsOfExperience" value={formData.yearsOfExperience} onChange={handleChange} min="0" className="formInput" />
            </label>
    
            <label className="formLabel">
              Province:
              <select name="addressProvince" value={formData.addressProvince} onChange={handleChange} className="formInput">
                <option value="">Select Province</option>
                {Object.keys(listOfAddress).map((province, index) => (
                  <option key={index} value={province}>
                    {province}
                  </option>
                ))}
              </select>
            </label>
            {formData.addressProvince && (
              <div>
                <label className="formLabel">
                  City:
                  <select name="addressTown" value={formData.addressTown} onChange={handleChange} className="formInput">
                    <option value="">Select City</option>
                    {listOfAddress[formData.addressProvince].map((city, index) => (
                      <option key={index} value={city}>
                        {city}
                      </option>
                    ))}
                  </select>
                </label>
              </div>
            )}
    
            <label className="formLabel">
              Facebook Link:
              <input type="text" name="facebookLink" value={formData.facebookLink} onChange={handleChange} className="formInput" />
            </label>
            <label className="formLabel">
              Instagram Link:
              <input type="text" name="instagramLink" value={formData.instagramLink} onChange={handleChange} className="formInput" />
            </label>
            <label className="formLabel">
              Snapchat Link:
              <input type="text" name="snapchatLink" value={formData.snapchatLink} onChange={handleChange} className="formInput" />
            </label>
            <label className="formLabel">
              TikTok Link:
              <input type="text" name="tiktokLink" value={formData.tiktokLink} onChange={handleChange} className="formInput" />
            </label>
            <label className="formLabel">
              Personal Description:
              <textarea name="personalDescription" value={formData.personalDescription} onChange={handleChange} className="formInput" />
            </label>
            <label className="formLabel">
              What do you think is your current gym level:
              <select name="gymLevel" value={formData.gymLevel} onChange={handleChange} className="formInput">
                <option value="BEGINNER">Beginner</option>
                <option value="INTERMEDIATE">Intermediate</option>
                <option value="ADVANCED">Advanced</option>
              </select>
            </label>
    
            <button type="submit" className="submitButton">Submit</button>
          </form>
        </div>
      );
}

export default SignIn;

{/* <label>
                Profile Picture:
                <input type="file" name="profilePicture" onChange={handleChange} />
            </label> */}