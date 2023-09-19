import { createContext, useContext, useState } from "react";

export const UserContext = createContext();

export function UserProvider({children}){
    const [userId,setUserId] = useState();
    return (
        <UserContext.Provider value = {{userId,setUserId}}>
         {children}
         </UserContext.Provider>
    )
}

export function useUser(){
    return useContext(UserContext);
}