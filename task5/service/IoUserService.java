package task5.service;

import task5.dao.User;
import task5.util.ValidationUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * @author Oleh Fliurkevych
 */
public class IoUserService {

  private static final Logger logger = Logger.getLogger(IoUserService.class.getName());

  public synchronized void writeNewUser(User user) {
    var file = getFile(user.getId());
    ValidationUtils.validateUser(file.exists(), false, user.getId());
    writeUser(file, user);
  }

  public synchronized void updateUser(User user) {
    var file = getFile(user.getId());
    ValidationUtils.validateUser(file.exists(), true, user.getId());
    writeUser(file, user);
  }

  private synchronized void writeUser(File file, User user) {
    try (FileOutputStream fos = new FileOutputStream(file);
      ObjectOutputStream oos = new ObjectOutputStream(fos)) {
      logger.info(String.format("Write user with id [%s]", user.getId()));
      oos.writeObject(user);
    } catch (IOException ex) {
      logger.warning(ex.getMessage());
    }
  }

  private File getFile(UUID id) {
    String dirName = System.getProperty("user.dir") + "\\task5\\users";
    File dir = new File(dirName);
    return new File(dir, id + ".txt");
  }

}
