import axios from "axios";
const URL = 'http://localhost:8080/adresses';

const config = () => {
    return {
        headers : {
            "Authorization" : sessionStorage.getItem('token'),
            "Content-Type" : "application/json",
        }
    }
}



export const findByClient = async( client ) =>{


 
    try {
        const response = await axios.post(`${URL}/list`,  client , config());
        return response;    
        
    } catch (error) {
       console.error(error);
    }
    return null;
}




export const save = async({ client, rue, cod_postal, ville, complement, statut }) =>{

    try {
        return await axios.post(URL, {  client, rue, cod_postal, ville, complement, statut }, config());
    } catch (error) {
       throw error; 
    }
}



export const update = async({ id, client, rue, cod_postal, ville, complement, statut  }) => {
    try {
        return await axios.put(`${URL}/${id}`, { client, rue, cod_postal, ville, complement, statut}, config());        
    } catch (error) {
        console.log(error);
       throw error;     
    }
}
