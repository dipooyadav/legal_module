package com.nscs.SBMaster.Entity;

import jakarta.persistence.*;



@Entity
@Table(name="epm_partners")
public class Partners {


        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private int id;
        private String name;
        private String partnershipTypes_id;

        public int getId() {
        return id;
    }
        public void setId(int id) {
        this.id = id;
    }
        public String getName() {
        return name;
    }
        public void setName(String name) {
        this.name = name;
    }

        public String getPartnershipTypes_id() {
        return partnershipTypes_id;
    }
        public void setPartnershipTypes_id(String partnershipTypes_id) {
        this.partnershipTypes_id=partnershipTypes_id;
    }
     /*
        public String execute()
        {
            HttpServletRequest request = ServletActionContext.getRequest();
            this.setPartnershipTypes(PartnershipTypesDao.getData(request.getParameter("partnershiptypeId")));
            PartnersDao.savePartners(this);
            return "success";
        }
         */

    }

