company(id_company , name_company,adress_company,description_company)

jobAD(id_jobAD ,title,position, #id_company,#id_category,description_jobAD)

category(id_category , name_category)

applyer(id_applyer , name_applyer,experience_years,email)

application(message, Date,#id_applyer,id_jobAD)
