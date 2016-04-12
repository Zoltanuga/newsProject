package by.itacademy.navigation;

import by.itacademy.navigation.impl.*;

public enum CommandsEnum {
    MAIN {
        @Override
        public Command createCommand() {
            return new MainCommand();
        }
    },
    ADD_NEWS {
        @Override
        public Command createCommand() {
            return new AddNewsCommand();
        }
    },
    LIST_NEWS {
        @Override
        public Command createCommand() {
            return new ListNewsCommand();
        }
    },
    MAIN_REDIRECT {
        @Override
        public Command createCommand() {
            return new RedirectCommand();
        }
    },
    MAIN_REGISTER {
        @Override
        public Command createCommand() {
            return new RegisterCommand();
        }
    };

    public abstract Command createCommand();
}
