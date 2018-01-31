package ua.simpleproject.command;

public enum CommandEnum {

    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogOutCommand();
        }
    },
    REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }
    },
    OPENCHECK {
        {
            this.command = new OpenCheckCommand();
        }
    },
    OPENCURRENTCHECK {
        {
            this.command = new OpenCurrentCheckCommand();
        }
    },
    ADDPRODUCT {
        {
            this.command = new AddProductCommand();
        }
    },
    OUTX {
        {
            this.command = new OutXCommand();
        }
    },
    OUTZ {
        {
            this.command = new OutYCommand();
        }
    },
    CREATEPRODUCT {
        {
            this.command = new CreateProductCommand();
        }
    },
    ADDPRODUCTSTOCK {
        {
            this.command = new AddProductStockCommand();
        }
    },
    GETPRODUCTSTOCK {
        {
            this.command = new GetProductStockCommand();
        }
    },
    CLOSECHECK {
        {
            this.command = new CloseChequeCommand();
        }
    },
    PAGINATIONSTOCK {
        {
            this.command = new PaginationStockCommand();
        }
    },
    DELETECHEQUE{
        {
            this.command = new DeleteChequeCommand();
        }
    },
    GOTOMAIN {
        {
            this.command = new GoToMainCommand();
        }
    },
    GOTOREGISTRATION{
        {
            this.command = new GoToRegistrationCommand();
        }
    },
    PAYANDPRINTCHEQUE{
        {
            this.command = new PayAndPrintChequeCommand();
        }
    }
    ;
    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
