import Header from "../components/Header";

function MainLayout({ children }) {
   return (
      <>
         <Header />
         <main>{children}</main>
      </>
   );
}

export default MainLayout;
