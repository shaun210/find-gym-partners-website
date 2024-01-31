import React, { useEffect, useState } from "react";
import { getProfilePicture } from "../../../api/MemberApi";

const EditProfile = () => {
  const [imageUrl, setImageUrl] = useState(null);
  const storedMember = JSON.parse(localStorage.getItem('member'));
  const currentUser = storedMember ? storedMember.username : '';
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await getProfilePicture(currentUser);
        const dataUri = await convertBlobToDataUri(response);
        // Update the state with the data URI
        setImageUrl(dataUri);
      } catch (error) {
        console.error('Error fetching profile picture:', error);
      }
    };

    fetchData();
  }, [currentUser]);

  const convertBlobToDataUri = (blob) => {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();

      reader.onloadend = () => {
        resolve(reader.result);
      };

      reader.onerror = (error) => {
        reject(error);
      };

      reader.readAsDataURL(blob);
    });
  };


  return (
    <div>
      {imageUrl ? (
        <img src={imageUrl} alt="Profile" style={{ width: '100px', height: '100px', borderRadius: '50%', marginTop: '300px' }} />
      ) : (
        <div>Loading...</div>
      )}
    </div>
  );
};

export default EditProfile;
