import React, { createContext, useState } from "react";

const UIContext = createContext();

function UIContextProvider({ children }) {
   const [isOpen, setIsOpen] = useState(false);
   const handleOpen = () => setIsOpen(true);
   const handleClose = () => setIsOpen(false);

   return (
      <UIContext.Provider value={{ isOpen, handleOpen, handleClose }}>
         {children}
      </UIContext.Provider>
   );
}
// Export có tên chuẩn ES Module:
// export { UIContextProvider, useUIContext };
export default UIContextProvider;
// export default useUIContext;
