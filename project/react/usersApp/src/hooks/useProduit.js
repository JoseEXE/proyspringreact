import { useContext, useReducer, useState } from "react";
import { useNavigate } from "react-router-dom";
import { produitReducer } from "../reducer/produitReducer";
import { AuthContext } from "../auth/context/AuthContext";
import { catProduitReducer } from "../reducer/catProduitReducer";
import { findAllPages, save, update } from "../services/produitService";
import { findAll } from "../services/catProduitService";
import Swal from "sweetalert2";


const initTypePlat = ["Chaud", "Froid", "Mix"];

const initListe = [];

const iniPaginator = {};

const initEstructure = {
    id: 0,
    catProduit: {
        id: 0,
    },
    user: {
        id: 0,
    },
    code: '',
    nom: '',
    description: '',
    prix: 0.00,
    type_plat: '',
    createdOn: '',
    updatedOn: '',
    statut: true,

}

const initErrors = {
    code: '',
    nom: '',
    description: '',
    prix: 0.00,
    type_plat: '',
}

export const useProduit = () =>{

    const { login, handlerLogout } = useContext(AuthContext);
    const [selectRegistre, setSelectRegistre] = useState(initEstructure);
    const [registres, dispatch] = useReducer(produitReducer, initListe);
    const [registresCatProd, dispatchCatProd] = useReducer(catProduitReducer, initListe); /* Cat_Prod */
    const [listTypePlat, setSelectTypePlat] = useState(initTypePlat);
    const [paginator, dispatchPaginator] = useReducer(produitReducer, iniPaginator);
    const [errors, setErrors] = useState(initErrors);
    const [visibleForm, setVisibleForm] = useState(false); 
    const [optionForm, setOptionForm ] = useState("");
    const navigation = useNavigate();

    /* READ Registers */
    /*======================================================*/
    const getRegistres = async(page=0) =>{ 
        const result = await findAllPages(page); //findAll()
        dispatch({
            type: 'loading',
            payload: result.data,
        });
        dispatchPaginator({
            type: 'loadingPagination',
            payload: result.data,
        })
    }

    const getCatProd = async() =>{

        const resultCatProd = await findAll(); //findAll()
        
        dispatchCatProd({
            type: 'loadingselect',
            payload: resultCatProd.data,
        });

    }

    

    //console.log("registresCatProd =>: "+ registresCatProd.length);
    console.log("registres =>: "+ registres.length);
    /*END READ Registers */
    /*======================================================*/

      /* CREATION ET MODIFICATION Produit*/
    /*======================================================*/
    const handlerAddRegistre = async(register) =>{
            if(!login.isAdmin) return;
            let response;
            try {
       
               // console.log(JSON.parse(JSON.stringify(register)));

                if(register.id === 0){
                    response = await save(register);
                }else{
                    console.log("UPDATE : :: : ");
                    response = await update(register);
                }
                
                dispatch({
        
                    type : (register.id === 0) ? 'addRegistre' : 'updateRegistre',
                    payload: response.data,
                })
              
     
                if(register.id === 0){
                Swal.fire(
                    'Produit enregistré!.',
                    'Le Produit a été enregistré avec succès !',
                    'success'
                    )
                    handerFormClose();
                    navigation('/catproduits');
                }else{
                    Swal.fire(
                    'Produit modifié!.',
                    'Le Produit a été actualisé avec succès !',
                    'success'
                    )
                    handerFormClose();
                    navigation('/produits');
                }
                    
                    
            } catch(error) {
                if(error.response && error.response.status == 400){
                    setErrors(error.response.data);
                }else if(error.response && error.response.status == 500 && error.response.data?.message?.includes('constraint')){
                    setErrors({nom: "Le code produit est déjà utilisé !"});
                }else if(error.response?.status == 401){
                    handlerLogout();
                }else if(error.response.status == 403){
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: error.response.data?.headers, 
                        })

                    
                }else{
                    throw error;
                }
            }
    }
    /*END CREATION ET MODIFICATION Registre*/
    /*======================================================*/

    const handlerSelectRegistre = (registre, option) =>{
        if(option == "edit"){
            setOptionForm(option);
        }else{
            setOptionForm(option);
        }
        getCatProd();
        setSelectRegistre({...registre});
        setVisibleForm(true);
    }

    const handerFormClose = () =>{
        setVisibleForm(false);
        setSelectRegistre(initEstructure);
        setErrors({});
        getRegistres();
        getCatProd();        
    }

    
    const handlerFormOpen = () =>{
        getCatProd();
        setOptionForm('new');
        setVisibleForm(true);
    }

    return{
        handlerAddRegistre,
        handlerSelectRegistre,
        getRegistres,
        getCatProd,
        handlerFormOpen,
        handerFormClose,
        optionForm,
        visibleForm,
        initListe,
        selectRegistre,
        iniPaginator,
        initEstructure,
        initErrors,
        registres,
        registresCatProd,
        paginator,
        errors,
        listTypePlat,
    }

    
}