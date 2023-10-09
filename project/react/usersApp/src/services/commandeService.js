import axios from "axios";
const URL = 'http://localhost:8080/commandes';

const config = () => {
    return {
        headers : {
            "Authorization" : sessionStorage.getItem('token'),
            "Content-Type" : "application/json",
        }
    }
}



export const findAll = async() =>{
    try {
        const response = await axios.get(URL);
        return response;    
        
    } catch (error) {
       console.error(error);
    }
    return null;
}

export const findAllPages = async(page = 0) =>{
    try {
        const response = await axios.get(`${URL}/page/${page}`);
        return response;    
    } catch (error) {
       console.error(error);
    }
    return null;
}


export const findLast = async(page = 0) =>{
    try {
        const response = await axios.get(`${URL}/page/${page}`);
        return response;    
    } catch (error) {
       console.error(error);
    }
    return null;
}




export const save = async({ client, user, adresse, commentaire, totalHl, total, type_paiement, etat }) =>{
    console.log("Llega a send save");

    try {
        return await axios.post(URL, {  client, user, adresse, commentaire, totalHl, total, type_paiement, etat }, config());
    } catch (error) {
       throw error; 
    }
}



export const update = async({ id, client, user, adresse, commentaire, totalHl, total, type_paiement, etat }) => {
    try {
        return await axios.put(`${URL}/${id}`, { client, user, adresse, commentaire, totalHl, total, type_paiement, etat}, config());        
    } catch (error) {
        console.log(error);
       throw error;     
    }
}
