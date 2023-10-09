import {Navigate, Route, Routes} from "react-router-dom"
import { UsersPage } from "../pages/UsersPage";
import { NavBar } from "../components/layout/NavBar";
import { RegisterPage } from "../pages/RegisterPage";
import { UserProvider } from "../context/UserProvider";
import { AuthContext } from "../auth/context/AuthContext";
import { useContext } from "react";
import { UserChangePassPage } from "../pages/UserChangePassPage";
import { EtablissementPage } from "../pages/EtablissementPage";
import { EtablissementProvider } from "../context/etablissement/EtablissementProvider";
import { EtablissementRegPage } from "../pages/EtablissementRegPage";
import { CatProduitPage } from "../pages/CatProduitPage";
import { CatProduitProvider } from "../context/catproduit/CatProduitProvider";
import { ProduitProvider } from "../context/produit/ProduitProvider";
import { ProduitPage } from "../pages/ProduitPage";
import { ClientPage } from "../pages/ClientPage";
import { ClientProvider } from "../context/client/ClientProvider";
import { AdresseProvider } from "../context/adresse/AdresseProvider";
import { OffCanvas } from "../components/layout/OffCanvas";
import { CommandeProvider } from "../context/commande/CommandeProvider";

export const UserRoutes = () =>{
    const { login } = useContext(AuthContext);
     return (<>
    <UserProvider>
        <CatProduitProvider>
            <EtablissementProvider>
                <ProduitProvider>
                    <ClientProvider>
                        <AdresseProvider>
                            <CommandeProvider>
                                <NavBar />
                                <OffCanvas />
                                    <Routes>
                                        <Route path="users" element={ <UsersPage /> } />
                                        <Route path="users/page/:page" element={ <UsersPage /> } />
                                        <Route path="clients" element={ <ClientPage /> } />
                                        <Route path="clients/page/:page" element={ <ClientPage /> } />

                                        <Route path="etablissements" element={ <EtablissementPage /> } />
                                        <Route path="etablissements/page/:page" element={ <EtablissementPage /> } />
                                        <Route path="catproduits" element={ <CatProduitPage /> } />
                                        <Route path="catproduits/page/:page" element={ <CatProduitPage /> } />
                                        <Route path="produits" element={ <ProduitPage /> } />
                                        <Route path="produits/page/:page" element={ <ProduitPage /> } />
                                        {!login.isAdmin ||
                                            <>
                                                <Route path="users/register" element={ <RegisterPage /> } />
                                                <Route path="users/edit/:id" element={ <RegisterPage /> } />
                                                <Route path="etablissements/edit/:id" element={ <EtablissementRegPage /> } />
                                            </>
                                        }
                                        <Route path="users/pass/:id" element={ <UserChangePassPage /> } />
                                        <Route path="/" element={ <Navigate to="/users" /> } />
                                    </Routes>
                            </CommandeProvider>
                        </AdresseProvider>
                    </ClientProvider>
                </ProduitProvider>
            </EtablissementProvider>
        </CatProduitProvider>
    </UserProvider>
    </>);
}




